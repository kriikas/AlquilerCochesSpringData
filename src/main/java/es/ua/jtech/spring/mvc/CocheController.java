package es.ua.jtech.spring.mvc;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.ua.jtech.spring.modelo.Coche;
import es.ua.jtech.spring.negocio.CocheBO;

/**
 *
 * @author especialista
 */
@Controller
public class CocheController {

    @Autowired
    private CocheBO bo;


    /*---------------------- LISTAR TODOS LOS COCHES  -----------------------------------------*/
    @RequestMapping("/listar.do")
    public String listar(Model modelo, 
    		@RequestParam(value = "fecha", required = false) @DateTimeFormat(pattern="dd/MM/yy") Date fecha) {
    	List<Coche> coches;
		coches = bo.listar();
    	modelo.addAttribute("listado", coches);    		
        return "listar";
    }

    /*---------------------- ACTUALIZAR COCHE  -----------------------------------------*/
    @RequestMapping(value = "/editar.do", method = RequestMethod.GET)
    public String prepararEditar(Model modelo,@RequestParam(value = "matricula") String matricula) {
        modelo.addAttribute(bo.obtener(matricula));
        return "editar";
    }

    /*-----------------------INSERTAR NUEVO COCHE -------------------------------------*/
    @RequestMapping(value = "/crear.do", method = RequestMethod.GET)
    public String prepararCrear(Model modelo) {
        modelo.addAttribute(new Coche());
        return "crear";
    }

    @RequestMapping(value = "/crear.do", method = RequestMethod.POST)
    public String procesarCrear(@Valid Coche coche, BindingResult result, Model modelo) {
        if (result.hasErrors()) {
        	for (ObjectError error : result.getAllErrors()) {
        		System.err.println(error.getCode()+ ": " + error.getDefaultMessage());
        	}
             return "crear";
        } else {
        	bo.crear(coche);
            return "redirect:listar.do";
        }
    }
}
