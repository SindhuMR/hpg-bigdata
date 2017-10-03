/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package org.opencb.hpg.bigdata.app.cli.local.avro;
@SuppressWarnings("all")
public class VariantTraitAssociation {
   private java.util.List<ClinVar> clinvar;
   private java.util.List<Gwas> gwas;
   private java.util.List<Cosmic> cosmic;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public VariantTraitAssociation() {}

  /**
   * All-args constructor.
   */
  public VariantTraitAssociation(java.util.List<ClinVar> clinvar, java.util.List<Gwas> gwas, java.util.List<Cosmic> cosmic) {
    this.clinvar = clinvar;
    this.gwas = gwas;
    this.cosmic = cosmic;
  }

  /**
   * Gets the value of the 'clinvar' field.
   */
  public java.util.List<ClinVar> getClinvar() {
    return clinvar;
  }

  /**
   * Sets the value of the 'clinvar' field.
   * @param value the value to set.
   */
  public void setClinvar(java.util.List<ClinVar> value) {
    this.clinvar = value;
  }

  /**
   * Gets the value of the 'gwas' field.
   */
  public java.util.List<Gwas> getGwas() {
    return gwas;
  }

  /**
   * Sets the value of the 'gwas' field.
   * @param value the value to set.
   */
  public void setGwas(java.util.List<Gwas> value) {
    this.gwas = value;
  }

  /**
   * Gets the value of the 'cosmic' field.
   */
  public java.util.List<Cosmic> getCosmic() {
    return cosmic;
  }

  /**
   * Sets the value of the 'cosmic' field.
   * @param value the value to set.
   */
  public void setCosmic(java.util.List<Cosmic> value) {
    this.cosmic = value;
  }
}