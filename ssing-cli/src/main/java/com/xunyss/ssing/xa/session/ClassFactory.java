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
}
