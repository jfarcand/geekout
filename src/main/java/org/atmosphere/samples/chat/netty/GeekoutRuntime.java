/*
 * Copyright 2014 Jeanfrancois Arcand
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.atmosphere.samples.chat.netty;

import org.atmosphere.nettosphere.Config;
import org.atmosphere.nettosphere.Nettosphere;
import org.atmosphere.samples.chat.custom.Chat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GeekoutRuntime {

    private static final Logger logger = LoggerFactory.getLogger(GeekoutRuntime.class);

    public static void main(String[] args) throws IOException {
        Config.Builder b = new Config.Builder();
        b.resource(Chat.class)
                .resource("/Users/jfarcand/workspace/geekout//src/main/webapp")
                .port(8081).host("0.0.0.0").build();
        Nettosphere s = new Nettosphere.Builder().config(b.build()).build();
        s.start();
        String a = "";

        logger.info("NettoSphere Chat Server started on port {}", 8080);
        logger.info("Type quit to stop the server");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (!(a.equals("quit"))) {
            a = br.readLine();
        }
        System.exit(-1);
    }

}
