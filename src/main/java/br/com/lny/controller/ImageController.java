package br.com.lny.controller;

import br.com.lny.model.ErrorInfo;
import br.com.lny.model.Image;
import br.com.lny.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Image> getImages() {
        return imageService.list();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Image getImage(@PathVariable Long id) {
        return imageService.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Image saveImage(@RequestBody Image image) {
        return imageService.saveImage(image);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Image updateImage(@RequestBody Image image) {
        return imageService.updateImage(image);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
    }

    @RequestMapping(value = "/listWithAllProperties", method = RequestMethod.GET)
    public List<Image> listImagesWithAllProperties() {
        return imageService.listImagesWithAllProperties();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    ErrorInfo
    handleBadRequest(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(req.getRequestURL().toString(), ex);
    }
}
