package com.fly.gateway.socket.handlers;

import com.fly.gateway.executor.SessionResult;
import com.fly.gateway.proxy.IGenericReference;
import com.fly.gateway.session.DefaultGatewaySessionFactory;
import com.fly.gateway.session.GatewaySession;
import com.fly.gateway.socket.agreement.AgreementConstants;
import com.fly.gateway.socket.agreement.GatewayResultMessage;
import com.fly.gateway.socket.agreement.RequestParser;
import com.fly.gateway.socket.agreement.ResponseParser;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ProtocolDataHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final Logger logger = LoggerFactory.getLogger(ProtocolDataHandler.class);

    private final DefaultGatewaySessionFactory gatewaySessionFactory;

    public ProtocolDataHandler(DefaultGatewaySessionFactory gatewaySessionFactory) {
        this.gatewaySessionFactory = gatewaySessionFactory;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channel, FullHttpRequest request) throws Exception {
        try {
            logger.info("网关接收请求【消息】 uri：{} method：{}", request.uri(), request.method());
            RequestParser requestParser = new RequestParser(request);
            String uri = requestParser.getUri();
            if (null == uri) return;
            Map<String, Object> args = requestParser.parse();
            GatewaySession gatewaySession = gatewaySessionFactory.openSession(uri);
            IGenericReference genericReference = gatewaySession.getMapper();
            SessionResult result = genericReference.$invoke(args);
            // 3. 封装返回结果
            DefaultFullHttpResponse response = new ResponseParser().parse("0000".equals(result.getCode()) ? GatewayResultMessage.buildSuccess(result.getData()).setNode(node()) : GatewayResultMessage.buildError(AgreementConstants.ResponseCode._404.getCode(), "网关协议调用失败！").setNode(node()));
            channel.writeAndFlush(response);
        } catch (Exception e) {
            // 4. 封装返回结果
            DefaultFullHttpResponse response = new ResponseParser().parse(GatewayResultMessage.buildError(AgreementConstants.ResponseCode._502.getCode(), "网关协议调用失败！" + e.getMessage()).setNode(node()));
            channel.writeAndFlush(response);
        }
    }

    private String node(){
        return gatewaySessionFactory.getConfiguration().getHostName() + ":" + gatewaySessionFactory.getConfiguration().getPort();
    }
}
