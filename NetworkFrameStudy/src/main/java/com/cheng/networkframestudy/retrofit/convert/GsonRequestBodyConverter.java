package com.cheng.networkframestudy.retrofit.convert;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

/**
 * ClassName GsonRequestBodyConverter
 * Description
 * Company
 * author  youxuan  E-mail:xuanyouwu@163.com
 * date createTime：2015/12/24 12:39
 * version
 */
public class GsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
  private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
  private static final Charset UTF_8 = Charset.forName("UTF-8");

  private final Gson gson;
  private final Type type;

  GsonRequestBodyConverter(Gson gson, Type type) {
    this.gson = gson;
    this.type = type;
  }

  @Override public RequestBody convert(T value) throws IOException {
    Buffer buffer = new Buffer();
    Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
    try {
      gson.toJson(value, type, writer);
      writer.flush();
    } catch (IOException e) {
      throw new AssertionError(e); // Writing to Buffer does no I/O.
    }
    return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
  }
}