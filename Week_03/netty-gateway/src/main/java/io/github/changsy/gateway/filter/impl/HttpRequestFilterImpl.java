package io.github.changsy.gateway.filter.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.changsy.gateway.filter.HttpRequestFilter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;

import java.util.Map;

public class HttpRequestFilterImpl  implements HttpRequestFilter {


    @Override
    public void filter( FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().add("nio","changshengye");
    }
}
