/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.springboot.example.http.streaming;

import org.apache.camel.builder.RouteBuilder;

import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class HttpStreamingCamelServerRouter extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		restConfiguration()
				.component("platform-http");

		rest()
				.put("/test")

//				.produces(MediaType.TEXT_PLAIN_VALUE)
				.to("direct:getRequest");

		from("direct:getRequest")
//		from("platform-http:test")
				.log("handling input stream")
				.to("file:../data?fileName=output")
				.log("done streaming")
				.setBody(simple("done"));
	}
}
