/*
 * Portions of this software was developed by employees of the National Institute
 * of Standards and Technology (NIST), an agency of the Federal Government and is
 * being made available as a public service. Pursuant to title 17 United States
 * Code Section 105, works of NIST employees are not subject to copyright
 * protection in the United States. This software may be subject to foreign
 * copyright. Permission in the United States and in foreign countries, to the
 * extent that NIST may hold copyright, to use, copy, modify, create derivative
 * works, and distribute this software and its documentation without fee is hereby
 * granted on a non-exclusive basis, provided that this notice and disclaimer
 * of warranty appears in all copies.
 *
 * THE SOFTWARE IS PROVIDED 'AS IS' WITHOUT ANY WARRANTY OF ANY KIND, EITHER
 * EXPRESSED, IMPLIED, OR STATUTORY, INCLUDING, BUT NOT LIMITED TO, ANY WARRANTY
 * THAT THE SOFTWARE WILL CONFORM TO SPECIFICATIONS, ANY IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, AND FREEDOM FROM
 * INFRINGEMENT, AND ANY WARRANTY THAT THE DOCUMENTATION WILL CONFORM TO THE
 * SOFTWARE, OR ANY WARRANTY THAT THE SOFTWARE WILL BE ERROR FREE.  IN NO EVENT
 * SHALL NIST BE LIABLE FOR ANY DAMAGES, INCLUDING, BUT NOT LIMITED TO, DIRECT,
 * INDIRECT, SPECIAL OR CONSEQUENTIAL DAMAGES, ARISING OUT OF, RESULTING FROM,
 * OR IN ANY WAY CONNECTED WITH THIS SOFTWARE, WHETHER OR NOT BASED UPON WARRANTY,
 * CONTRACT, TORT, OR OTHERWISE, WHETHER OR NOT INJURY WAS SUSTAINED BY PERSONS OR
 * PROPERTY OR OTHERWISE, AND WHETHER OR NOT LOSS WAS SUSTAINED FROM, OR AROSE OUT
 * OF THE RESULTS OF, OR USE OF, THE SOFTWARE OR SERVICES PROVIDED HEREUNDER.
 */

package gov.nist.secauto.oscal.tools.cli.core.commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public enum Format {
  XML(gov.nist.secauto.metaschema.binding.io.Format.XML, ".xml"),
  JSON(gov.nist.secauto.metaschema.binding.io.Format.JSON, ".json"),
  YAML(gov.nist.secauto.metaschema.binding.io.Format.YAML, ".yml");

  private static final Map<gov.nist.secauto.metaschema.binding.io.Format, Format> FORMAT_MAP;

  private final String defaultExtension;
  private final gov.nist.secauto.metaschema.binding.io.Format bindingFormat;

  static {
    Map<gov.nist.secauto.metaschema.binding.io.Format, Format> map = new HashMap<>(); // NOPMD - HashMap is ok here
    for (Format format : Format.values()) {
      map.put(format.getBindingFormat(), format);
    }
    FORMAT_MAP = Collections.unmodifiableMap(map);
  }

  public static Format lookup(gov.nist.secauto.metaschema.binding.io.Format format) {
    return FORMAT_MAP.get(format);
  }

  public static List<String> names() {
    return Arrays.stream(Format.values())
        .map(format -> format.name().toLowerCase(Locale.ROOT))
        .collect(Collectors.toUnmodifiableList());
  }

  Format(gov.nist.secauto.metaschema.binding.io.Format bindingFormat, String defaultExtension) {
    this.bindingFormat = bindingFormat;
    this.defaultExtension = defaultExtension;
  }

  public gov.nist.secauto.metaschema.binding.io.Format getBindingFormat() {
    return bindingFormat;
  }

  public String getDefaultExtension() {
    return defaultExtension;
  }

}