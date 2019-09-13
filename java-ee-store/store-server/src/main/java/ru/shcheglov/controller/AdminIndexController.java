package ru.shcheglov.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * @author Alexey Shcheglov
 * @version dated 19.01.2019
 */

@ViewScoped
@ManagedBean
@URLMapping(
        id = "admin-index",
        pattern = "/admin/admin-index",
        viewId = "/WEB-INF/faces/admin-index.xhtml"
)
public class AdminIndexController implements Serializable {

}
