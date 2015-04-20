package com.xunyss.ssing.xa.dataset  ;

import com4j.*;

/**
 * Defines methods to create COM objects
 */
public abstract class ClassFactory {
  private ClassFactory() {} // instanciation is not allowed


  /**
   * XAQuery Class
   */
  public static com.xunyss.ssing.xa.dataset.IXAQuery createXAQuery() {
    return COM4J.createInstance( com.xunyss.ssing.xa.dataset.IXAQuery.class, "{781520A9-4C8C-433B-AA6E-EE9E94108639}" );
  }

  /**
   * XAReal Class
   */
  public static com.xunyss.ssing.xa.dataset.IXAReal createXAReal() {
    return COM4J.createInstance( com.xunyss.ssing.xa.dataset.IXAReal.class, "{4D654021-F9D9-49F7-B2F9-6529A19746F7}" );
  }
}
