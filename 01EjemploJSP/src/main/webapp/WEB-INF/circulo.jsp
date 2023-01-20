
<%
    int radio = 5;
    if(request.getParameter("radio") != null){
        radio = Integer.parseInt(request.getParameter("radio"));
    }
    out.println("<p>Circulo de radio: " + radio + "</p>");
    out.println("<p>Area: " + Math.PI*radio*radio + "</p>");
    out.println("<p>Perimetro " + Math.PI*2*radio + "</p>");
%>