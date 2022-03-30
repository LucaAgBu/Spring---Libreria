package libreria.libreria.controladores;

import java.util.List;
import libreria.libreria.Errores.ErrorServicio;
import libreria.libreria.entidades.Editorial;
import libreria.libreria.repositorios.EditorialRepositorio;
import libreria.libreria.servicios.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class EditorialController {
    
    @Autowired
    private EditorialService editorialService;
    
    @Autowired 
    private EditorialRepositorio editorialRepositorio;
    
    @GetMapping("/editoriales")
    public String editoriales(ModelMap modelo) throws ErrorServicio{
        List<Editorial> editoriales = editorialRepositorio.listaEditoriales();
        modelo.put("editoriales", editoriales);
        
        return "editoriales.html";
    }
    
    
    
    @PostMapping("/ingresarEditorial")
    public String ingresarEditorial(ModelMap modelo, @RequestParam String nombre) throws ErrorServicio {
        try {
            editorialService.ingresarEditorial(nombre);
        } catch (ErrorServicio ex) {
            modelo.put("error", ex.getMessage());
        //    Logger.getLogger(PortalController.class.getName()).log(Level.SEVERE, null, ex);
            return "editoriales.html";
        }
        modelo.put("exito", "Se ingresaron los datos correctamente");
        return editoriales(modelo);
    }

       
          
        
         @PostMapping("/bajaEditorial")
        public String BajaEditorial(ModelMap modelo, @RequestParam String idEditorial) throws ErrorServicio{
        try {
            editorialService.Baja(idEditorial);
        } catch (Exception ex) {
         modelo.put("error", ex.getMessage());
        return editoriales(modelo)  ;
        }
        modelo.put("exito", "Se dio de baja correctamente   ");
        return editoriales(modelo);
    }        
        
        @PostMapping("/altaEditorial")
        public String AltaEditorial(ModelMap modelo, @RequestParam String idEditorial) throws ErrorServicio{
        try {
            editorialService.Alta(idEditorial);
        } catch (Exception ex) {
         modelo.put("error", ex.getMessage());
        return editoriales(modelo);
        }
        modelo.put("exito", "Se dio de alta correctamente");
        return editoriales(modelo);
    } 
        
    @PostMapping("/actualizarEditorial")
    public String actualizarEditorial(ModelMap modelo, @RequestParam String nombre, @RequestParam String idEditorial) throws ErrorServicio {
        try {
            editorialService.modificarEditorial(idEditorial, nombre);
        } catch (ErrorServicio ex) {
            modelo.put("error", ex.getMessage());
        //    Logger.getLogger(PortalController.class.getName()).log(Level.SEVERE, null, ex);
            return editoriales(modelo);
        }
        modelo.put("exito", "Se actualizaron los datos correctamente");
        return editoriales(modelo);
    }
    
}
