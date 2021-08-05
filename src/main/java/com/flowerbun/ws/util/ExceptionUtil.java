package com.flowerbun.ws.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionUtil {

  public static String printStackTrace(Exception e) {
    StringWriter writer = new StringWriter();
    PrintWriter printWriter = new PrintWriter(writer);
    e.printStackTrace(printWriter);
    return writer.toString();
  }

}
