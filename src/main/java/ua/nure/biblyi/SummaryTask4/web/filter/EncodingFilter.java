package ua.nure.biblyi.SummaryTask4.web.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by dmitry on 18.01.17.
 */
public class EncodingFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(EncodingFilter.class);

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.debug("Filter initialization starts");
        encoding = filterConfig.getInitParameter("encoding");
        LOG.trace("Encoding from web.xml --> " + encoding);
        LOG.debug("Filter initialization finished");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOG.debug("EncodingFilter.doFilter start");

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        LOG.trace("Request uri --> " + httpRequest.getRequestURI());

        String requestEncoding = servletRequest.getCharacterEncoding();
        if (requestEncoding == null) {
            LOG.trace("Request encoding = null, set encoding --> " + encoding);
            servletRequest.setCharacterEncoding(encoding);
        }

        LOG.debug("EncodingFilter.doFilter finish");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        LOG.debug("EncodingFilter.destroy start");

        LOG.debug("EncodingFilter.destroy finish");
    }
}