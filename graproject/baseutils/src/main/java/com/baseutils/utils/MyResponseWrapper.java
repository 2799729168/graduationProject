package com.baseutils.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;
import java.util.Map;

/**
 * @author pb
 */
public class MyResponseWrapper extends HttpServletResponseWrapper {
    private ByteArrayOutputStream buffer;

    private ServletOutputStream out;

    public MyResponseWrapper(HttpServletResponse httpServletResponse)
    {
        super(httpServletResponse);
        buffer = new ByteArrayOutputStream();
        out = new WrapperOutputStream(buffer);
    }

    @Override
    public ServletOutputStream getOutputStream()
            throws IOException
    {
        return out;
    }
//
//    @Override
//    public PrintWriter getWriter() throws IOException {
//        return new PrintWriter(buffer);
//    }

    public ByteArrayOutputStream getBuffer(){
        return this.buffer;
    }

    class WrapperOutputStream extends ServletOutputStream
    {
        private ByteArrayOutputStream bos;

        public WrapperOutputStream(ByteArrayOutputStream bos)
        {
            this.bos = bos;
        }
//所有write最后执行的方法
        @Override
        public void write(int b)
                throws IOException
        {
            bos.write(b);
        }

        @Override
        public boolean isReady()
        {

            // TODO Auto-generated method stub
            return true;

        }

        @Override
        public void setWriteListener(WriteListener arg0)
        {

            // TODO Auto-generated method stub

        }
    }

}
