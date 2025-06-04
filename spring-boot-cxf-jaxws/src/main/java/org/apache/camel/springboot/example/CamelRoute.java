package org.apache.camel.springboot.example;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.springframework.stereotype.Component;

@Component
public class CamelRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("timer://sayHelloTimer?repeatCount=1&delay=2000")
                .setHeader(CxfConstants.OPERATION_NAME, constant("sayHello"))
                .setBody(constant("Roman"))
                .to("cxf://{{cxf.endpoint}}/service/hello"
                        + "?serviceClass=org.apache.camel.springboot.example.Hello");
    }
}
