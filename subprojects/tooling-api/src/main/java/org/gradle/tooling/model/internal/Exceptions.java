/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.tooling.model.internal;

import org.gradle.tooling.exceptions.UnsupportedOperationConfigurationException;
import org.gradle.tooling.model.UnsupportedMethodException;

public class Exceptions {

    public final static String INCOMPATIBLE_VERSION_HINT =
            "Most likely the model of that type is not supported in the target Gradle version."
            + "\nTo resolve the problem you can change/upgrade the Gradle version the tooling api connects to.";

    public static UnsupportedMethodException unsupportedMethod(String method) {
        return new UnsupportedMethodException(formatUnsupportedModelMethod(method));
    }

    private static String formatUnsupportedModelMethod(String method) {
        return String.format("Unsupported method: %s."
                + "\nThe version of Gradle you connect to does not support that method."
                + "\nTo resolve the problem you can change/upgrade the target version of Gradle you connect to."
                + "\nAlternatively, you can ignore this exception and read other information from the model."
                , method);
    }

    public static UnsupportedOperationConfigurationException unsupportedOperationConfiguration(String operation) {
        //we only need that cause for backwards-compatibility.
        UnsupportedMethodException cause = new UnsupportedMethodException(operation);
        return new UnsupportedOperationConfigurationException(String.format("Unsupported configuration: %s."
                + "\nYou configured the LongRunningOperation (ModelBuilder or BuildLauncher) with an unsupported option."
                + "\nThe version of Gradle you connect to does not support this configuration option."
                + "\nTo resolve the problem you can change/upgrade the target version of Gradle you connect to."
                + "\nAlternatively, you may stop using this configuration option."
                , operation), cause);
    }
}
