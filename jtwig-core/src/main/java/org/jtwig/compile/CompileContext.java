/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jtwig.compile;

import org.jtwig.compile.config.CompileConfiguration;
import org.jtwig.content.model.compilable.Sequence;
import org.jtwig.exception.ParseException;
import org.jtwig.exception.ResourceException;
import org.jtwig.parser.JtwigParser;
import org.jtwig.resource.JtwigResource;

import org.jtwig.content.model.Template;

public class CompileContext {
    private JtwigResource resource;
    private final JtwigParser parser;
    private final CompileConfiguration configuration;
    private Sequence parent;

    public CompileContext(JtwigResource resource, JtwigParser parser, CompileConfiguration configuration) {
        this.resource = resource;
        this.parser = parser;
        this.configuration = configuration;
        this.parent = null;
    }

    public CompileContext withParent(Sequence element) {
        this.parent = element;
        return this;
    }

    public boolean hasParent () {
        return parent != null;
    }

    public Sequence parent () {
        return parent;
    }

    public JtwigResource retrieve(String relativePath) throws ResourceException {
        return resource.resolve(relativePath);
    }

    public Template parse (JtwigResource resource) throws ParseException {
        return parser.parse(resource);
    }

    public CompileContext clone() {
        CompileContext compileContext = new CompileContext(resource, parser, configuration);
        compileContext.withParent(parent);
        return compileContext;
    }

    public CompileContext withResource(JtwigResource retrieve) {
        this.resource = retrieve;
        return this;
    }
}
