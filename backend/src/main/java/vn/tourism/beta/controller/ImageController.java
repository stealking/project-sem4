package vn.tourism.beta.controller;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.tourism.beta.storage.StorageProperties;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
@RestController
public class ImageController {
//    private final Path p = Paths.get("/Users/devin/Documents/workspace-sts-3.8.1.RELEASE/testing/src/main/resources/static/uploads/images");

//    @RequestMapping(value={ "/", "/home"})
//    public String home(){
//        return "home";
//    }
//
//    @RequestMapping(value = "/post_upload_avatar_file", method = RequestMethod.POST)
//    @ResponseBody
//    public Object uploadAvatarFile(@RequestParam("uploadfile") MultipartFile uploadfile) {
//        JSONObject resJsonData=new JSONObject();
//        try {
//            if(uploadfile.isEmpty()){
//                System.out.println("Empty");
//            }
//
//            Files.copy(uploadfile.getInputStream(), p.resolve(uploadfile.getOriginalFilename()));
//
//            resJsonData.put("status", 200);
//            resJsonData.put("message", "Success!");
//            resJsonData.put("data", uploadfile.getOriginalFilename());
//        }catch (Exception e) {
//            System.out.println(e.getMessage());
//            resJsonData.put("status", 400);
//            resJsonData.put("message", "Upload Image Error!");
//            resJsonData.put("data", "");
//        }
//        return resJsonData.toString();
//    }

    @GetMapping("upload/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serverFile(@PathVariable String filename){
        Resource file = loadAsResource(filename);
        return ResponseEntity
                .ok()
                .body(file);
    }

    public Resource loadAsResource(String filename) {
        try {
            StorageProperties properties = new StorageProperties();
            Path p = Paths.get(properties.getLocation());
            Path file = p.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                System.out.println("no file");
            }
        } catch (MalformedURLException e) {
            System.out.println(e);
        }
        return null;
    }

//    public Stream<Path> loadAll() {
//        try {
//            return Files.walk(p, 1)
//                    .filter(path -> !path.equals(p))
//                    .map(path -> p.relativize(path));
//        } catch (IOException e) {
//            System.out.println(e);
//        }
//        return null;
//    }
}
