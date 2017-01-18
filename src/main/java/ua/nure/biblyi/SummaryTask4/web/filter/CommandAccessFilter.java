package ua.nure.biblyi.SummaryTask4.web.filter;


import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.Path;
import ua.nure.biblyi.SummaryTask4.db.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Created by dmitry on 17.01.17.
 */


public class CommandAccessFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(CommandAccessFilter.class);

    private Map<Role, List<String>> accessMap = new HashMap<Role, List<String>>();
    private List<String> commons = new ArrayList<String>();
    private List<String> adminManager = new ArrayList<String>();
    private List<String> outOfControl = new ArrayList<String>();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.debug("CommandAccessFilter.init start");
        List<String> managerList = asList(filterConfig.getInitParameter("manager"));
        List<String> adminList = asList(filterConfig.getInitParameter("admin"));
        adminList.addAll(managerList);
        accessMap.put(Role.ADMIN, adminList);
        accessMap.put(Role.CLIENT, asList(filterConfig.getInitParameter("client")));
        accessMap.put(Role.MANAGER, managerList);

        LOG.trace("Access map --> " + accessMap);

        // commons
        commons = asList(filterConfig.getInitParameter("common"));
        LOG.trace("Common commands --> " + commons);

        // out of control
        outOfControl = asList(filterConfig.getInitParameter("out-of-control"));
        LOG.trace("Out of control commands --> " + outOfControl);

        LOG.debug("CommandAccessFilter.init finish");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOG.debug("CommandAccessFilter.doFilter start");

        if (accessAllowed(servletRequest)) {
            LOG.debug("Filter finished");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String errorMessasge = "You do not have permission to access the requested resource";

            servletRequest.setAttribute("errorMessage", errorMessasge);
            LOG.trace("Set the request attribute: errorMessage --> " + errorMessasge);

            servletRequest.getRequestDispatcher(Path.PAGE_ERROR_PAGE)
                    .forward(servletRequest, servletResponse);
        }

        LOG.debug("CommandAccessFilter.doFilter finish");
    }

    @Override
    public void destroy() {
        LOG.debug("CommandAccessFilter.destroy start");

        LOG.debug("CommandAccessFilter.destroy finish");
    }


    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String commandName = request.getParameter("command");
        LOG.trace("Command name --> " + commandName);
        if (commandName == null || commandName.isEmpty()) {
            return false;
        }

        if (outOfControl.contains(commandName)) {
            return true;
        }

        HttpSession session = httpRequest.getSession(false);
        if (session == null) {
            return false;
        }

        Role userRole = (Role) session.getAttribute("userRole");
        return userRole != null && (accessMap.get(userRole).contains(commandName) || commons.contains(commandName));

    }



    /**
     * Extracts parameter values from string.
     *
     * @param str
     *            parameter values string.
     * @return list of parameter values.
     */
    private List<String> asList(String str) {
        List<String> list = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }

}
