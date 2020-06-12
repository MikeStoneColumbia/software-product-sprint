// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import java.io.IOException;
import java.util.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {

    List<String> messages = new LinkedList<String>();

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

      String message = getParameter(request,"text-input","");
      toJson(message);
      response.setContentType("text/html");
      response.getWriter().println("<h1>Thank you for contributing your message. Go back to the site to see it.</h1>");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    //response.setContentType("text/html;");
    response.setContentType("application/json");
    //response.getWriter().println("<h1>Hello Michael Stone</h1>");
    response.getWriter().println(messages.toString());
  }


  private void toJson(String message){

      if(messages.isEmpty())
        messages.add("{ \"msg\": \""+message+"\"}");
    
      else
        messages.add("{ \"msg\": \""+message+"\"}");
      
  }

  private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }

}
