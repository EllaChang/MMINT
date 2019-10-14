/**
 * Generated from platform:/resource/MASE19/src/examples/queries.vql
 */
package examples;

import examples.AsilBConnectedModelElems;
import examples.HighlyConnectedModelElems;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;

/**
 * A pattern group formed of all public patterns defined in queries.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file queries.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package examples, the group contains the definition of the following patterns: <ul>
 * <li>asilBConnectedModelElems</li>
 * <li>highlyConnectedModelElems</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class Queries extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Queries instance() {
    if (INSTANCE == null) {
        INSTANCE = new Queries();
    }
    return INSTANCE;
  }
  
  private static Queries INSTANCE;
  
  private Queries() {
    querySpecifications.add(AsilBConnectedModelElems.instance());
    querySpecifications.add(HighlyConnectedModelElems.instance());
  }
  
  public AsilBConnectedModelElems getAsilBConnectedModelElems() {
    return AsilBConnectedModelElems.instance();
  }
  
  public AsilBConnectedModelElems.Matcher getAsilBConnectedModelElems(final ViatraQueryEngine engine) {
    return AsilBConnectedModelElems.Matcher.on(engine);
  }
  
  public HighlyConnectedModelElems getHighlyConnectedModelElems() {
    return HighlyConnectedModelElems.instance();
  }
  
  public HighlyConnectedModelElems.Matcher getHighlyConnectedModelElems(final ViatraQueryEngine engine) {
    return HighlyConnectedModelElems.Matcher.on(engine);
  }
}