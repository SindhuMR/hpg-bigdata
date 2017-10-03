/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package org.opencb.hpg.bigdata.app.cli.local.avro;
@SuppressWarnings("all")
/** The consistency of evidences for a given phenotype. This aggregates all evidences for a given phenotype and all
    evidences with no phenotype associated (e.g.: in silico impact prediction, population frequency).
    This is based on the Confidence Information Ontology terms.

* `CIO_0000033`: congruent, all evidences are consistent. http://purl.obolibrary.org/obo/CIO_0000033
* `CIO_0000034`: conflict, there are conflicting evidences. This should correspond to a `VariantClassification` of
`uncertain_significance` for mendelian disorders. http://purl.obolibrary.org/obo/CIO_0000034
* `CIO_0000035`: strongly conflicting. http://purl.obolibrary.org/obo/CIO_0000035
* `CIO_0000036`: weakly conflicting. http://purl.obolibrary.org/obo/CIO_0000036 */
@org.apache.avro.specific.AvroGenerated
public enum ConsistencyStatus { 
  congruent, conflict, weakly_conflicting, strongly_conflicting  ;
}