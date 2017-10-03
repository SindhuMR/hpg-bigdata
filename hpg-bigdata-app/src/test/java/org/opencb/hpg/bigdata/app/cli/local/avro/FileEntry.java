/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package org.opencb.hpg.bigdata.app.cli.local.avro;
@SuppressWarnings("all")
public class FileEntry {
  /** * Unique identifier of the source file. */
   private String fileId;
  /** * Original call position for the variant, if the file was normalized.
         *
         * {position}:{reference}:{alternate}(,{other_alternate})*:{allele_index} */
   private String call;
  /** * Optional attributes that probably depend on the format of the file the
         * variant was initially read from. */
   private java.util.Map<String,String> attributes;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public FileEntry() {}

  /**
   * All-args constructor.
   */
  public FileEntry(String fileId, String call, java.util.Map<String,String> attributes) {
    this.fileId = fileId;
    this.call = call;
    this.attributes = attributes;
  }

  /**
   * Gets the value of the 'fileId' field.
   * * Unique identifier of the source file.   */
  public String getFileId() {
    return fileId;
  }

  /**
   * Sets the value of the 'fileId' field.
   * * Unique identifier of the source file.   * @param value the value to set.
   */
  public void setFileId(String value) {
    this.fileId = value;
  }

  /**
   * Gets the value of the 'call' field.
   * * Original call position for the variant, if the file was normalized.
         *
         * {position}:{reference}:{alternate}(,{other_alternate})*:{allele_index}   */
  public String getCall() {
    return call;
  }

  /**
   * Sets the value of the 'call' field.
   * * Original call position for the variant, if the file was normalized.
         *
         * {position}:{reference}:{alternate}(,{other_alternate})*:{allele_index}   * @param value the value to set.
   */
  public void setCall(String value) {
    this.call = value;
  }

  /**
   * Gets the value of the 'attributes' field.
   * * Optional attributes that probably depend on the format of the file the
         * variant was initially read from.   */
  public java.util.Map<String,String> getAttributes() {
    return attributes;
  }

  /**
   * Sets the value of the 'attributes' field.
   * * Optional attributes that probably depend on the format of the file the
         * variant was initially read from.   * @param value the value to set.
   */
  public void setAttributes(java.util.Map<String,String> value) {
    this.attributes = value;
  }
}