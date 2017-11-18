package br.com.lny.controller;

import br.com.lny.model.ErrorInfo;
import br.com.lny.model.Image;
import br.com.lny.model.ImageFilter;
import br.com.lny.model.ProductFilter;
import br.com.lny.service.ImageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    private ImageFilter imageFilter = new ImageFilter();

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getImages() throws JsonProcessingException {
        FilterProvider filters = new SimpleFilterProvider().addFilter("imageFilter", imageFilter.getBasicProperties());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writer(filters).writeValueAsString(imageService.list());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getImage(@PathVariable Long id) throws JsonProcessingException {
        FilterProvider filters = new SimpleFilterProvider().addFilter("imageFilter", imageFilter.getBasicProperties());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writer(filters).writeValueAsString(imageService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String saveImage(@RequestBody Image image) throws JsonProcessingException {
        FilterProvider filters = new SimpleFilterProvider().addFilter("imageFilter", imageFilter.getBasicProperties());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writer(filters).writeValueAsString(imageService.saveImage(image));
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public String updateImage(@RequestBody Image image) throws JsonProcessingException {
        FilterProvider filters = new SimpleFilterProvider().addFilter("imageFilter", imageFilter.getBasicProperties());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writer(filters).writeValueAsString(imageService.updateImage(image));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public void deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getImagesBasic(@PathVariable Long id) throws JsonProcessingException {
        FilterProvider filters = new SimpleFilterProvider().addFilter("imageFilter", imageFilter.getBasicProperties());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writer(filters).writeValueAsString(imageService.findByIdProduct(id));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    ErrorInfo
    handleBadRequest(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(req.getRequestURL().toString(), ex);
    }
}
