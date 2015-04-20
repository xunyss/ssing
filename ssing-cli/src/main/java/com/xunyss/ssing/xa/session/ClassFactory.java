package com.xunyss.ssing.xa.session  ;

import com4j.*;

/**
 * Defines methods to create COM objects
 */
public abstract class ClassFactory {
  private ClassFactory() {} // instanciation is not allowed


  /**
   * XASession Class
   */
  public static com.xunyss.ssing.xa.session.IXASession createXASession() {
    return COM4J.createInstance( com.xunyss.ssing.xa.session.IXASession.class, "{7FEF321C-6BFD-413C-AA80-541A275434A1}" );
  }
  
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
