/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package org.opencb.hpg.bigdata.app.cli.local.avro;
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Cosmic {
   private String mutationId;
   private String primarySite;
   private String siteSubtype;
   private String primaryHistology;
   private String histologySubtype;
   private String sampleSource;
   private String tumourOrigin;
   private String geneName;
   private String mutationSomaticStatus;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Cosmic() {}

  /**
   * All-args constructor.
   */
  public Cosmic(String mutationId, String primarySite, String siteSubtype, String primaryHistology, String histologySubtype, String sampleSource, String tumourOrigin, String geneName, String mutationSomaticStatus) {
    this.mutationId = mutationId;
    this.primarySite = primarySite;
    this.siteSubtype = siteSubtype;
    this.primaryHistology = primaryHistology;
    this.histologySubtype = histologySubtype;
    this.sampleSource = sampleSource;
    this.tumourOrigin = tumourOrigin;
    this.geneName = geneName;
    this.mutationSomaticStatus = mutationSomaticStatus;
  }

  /**
   * Gets the value of the 'mutationId' field.
   */
  public String getMutationId() {
    return mutationId;
  }

  /**
   * Sets the value of the 'mutationId' field.
   * @param value the value to set.
   */
  public void setMutationId(String value) {
    this.mutationId = value;
  }

  /**
   * Gets the value of the 'primarySite' field.
   */
  public String getPrimarySite() {
    return primarySite;
  }

  /**
   * Sets the value of the 'primarySite' field.
   * @param value the value to set.
   */
  public void setPrimarySite(String value) {
    this.primarySite = value;
  }

  /**
   * Gets the value of the 'siteSubtype' field.
   */
  public String getSiteSubtype() {
    return siteSubtype;
  }

  /**
   * Sets the value of the 'siteSubtype' field.
   * @param value the value to set.
   */
  public void setSiteSubtype(String value) {
    this.siteSubtype = value;
  }

  /**
   * Gets the value of the 'primaryHistology' field.
   */
  public String getPrimaryHistology() {
    return primaryHistology;
  }

  /**
   * Sets the value of the 'primaryHistology' field.
   * @param value the value to set.
   */
  public void setPrimaryHistology(String value) {
    this.primaryHistology = value;
  }

  /**
   * Gets the value of the 'histologySubtype' field.
   */
  public String getHistologySubtype() {
    return histologySubtype;
  }

  /**
   * Sets the value of the 'histologySubtype' field.
   * @param value the value to set.
   */
  public void setHistologySubtype(String value) {
    this.histologySubtype = value;
  }

  /**
   * Gets the value of the 'sampleSource' field.
   */
  public String getSampleSource() {
    return sampleSource;
  }

  /**
   * Sets the value of the 'sampleSource' field.
   * @param value the value to set.
   */
  public void setSampleSource(String value) {
    this.sampleSource = value;
  }

  /**
   * Gets the value of the 'tumourOrigin' field.
   */
  public String getTumourOrigin() {
    return tumourOrigin;
  }

  /**
   * Sets the value of the 'tumourOrigin' field.
   * @param value the value to set.
   */
  public void setTumourOrigin(String value) {
    this.tumourOrigin = value;
  }

  /**
   * Gets the value of the 'geneName' field.
   */
  public String getGeneName() {
    return geneName;
  }

  /**
   * Sets the value of the 'geneName' field.
   * @param value the value to set.
   */
  public void setGeneName(String value) {
    this.geneName = value;
  }

  /**
   * Gets the value of the 'mutationSomaticStatus' field.
   */
  public String getMutationSomaticStatus() {
    return mutationSomaticStatus;
  }

  /**
   * Sets the value of the 'mutationSomaticStatus' field.
   * @param value the value to set.
   */
  public void setMutationSomaticStatus(String value) {
    this.mutationSomaticStatus = value;
  }
}