package net.littlelite.smartbatch.controller.web

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Home page controller
 */
@Controller
class WebController
{
    private val logger = LoggerFactory.getLogger(WebController::class.java)

    /**
     * the REST request for / resource.
     *
     * @param model the HTTP request attributes. it will updated
     * with application's version.
     * @return the home page
     */
    @RequestMapping("/index")
    fun index(model: Model): String
    {
        logger.info("/index page")
        model.addAttribute("version", net.littlelite.smartbatch.VERSION)
        return "index"
    }

    /**
     * the REST request for / resource.
     *
     * @return redirect to the index page
     */
    @RequestMapping("/")
    fun home(): String
    {
        return "redirect:index"
    }

}
