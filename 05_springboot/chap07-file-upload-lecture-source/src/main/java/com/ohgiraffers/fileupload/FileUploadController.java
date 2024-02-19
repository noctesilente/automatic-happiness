package com.ohgiraffers.fileupload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.*;

/* 3-1 */
@Controller
public class FileUploadController {

    /* 6-1 */
    /* 설명. build로 파일 업로드 할 경로를 가져오기 위해 ResourceLoader 의존성 주입 받기 */
    @Autowired
    private ResourceLoader resourceLoader;

    /* 3-2 */
    @PostMapping("single-file")
    public String singleFileUpload(@RequestParam MultipartFile singleFile,
                                   @RequestParam String singleFileDescription,
                                   RedirectAttributes rttr) throws IOException {
        // file이기 때문에 String으로 받을 수 있음

        // 7-1 - RedirectAttributes rttr 추가

        /* 설명. encType = "multipart/form-data" 형태로 넘어온 파일은 MultipartFile 타입으로 받게 된다. */
        System.out.println("singleFile = " + singleFile);
        System.out.println("singleFileDescription = " + singleFileDescription);
        // 결과:
        // singleFile = org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@49b1fae2
        // singleFileDescription = pika


        /* 6-2 */
        // 사용자가 보낸 파일을 담아야 함
        // -> 빌드되는 곳에 저장을 바로 할 것!
        /* 설명. build 경로의 static에 있는 파일 업로드 할 곳의 경로를 받아온다.(미리 경로에 해당하는 디렉토리 생성 및 빌드 확인할 것) */
        Resource resource = resourceLoader.getResource("classpath:static/uploadFiles/img/single");
//        System.out.println("빌드된 single 디렉토리의 절대 경로: " + resource.getFile().getAbsolutePath());
        String filePath = resource.getFile().getAbsolutePath();

        /* 6-3 */
        /* 설명. 사용자가 넘긴 파일을 확인하고, rename 해 보자. (우리는 날짜 형식 말고 UUID 클래스를 활용해서 rename 함) */
        // 넘긴 파일부터 확인하기
        String originFileName = singleFile.getOriginalFilename();
        System.out.println("originFileName = " + originFileName);

        // 사용자가 올린 파일의 확장자를 우선 잘라서 보관한 다음 생성하고 확장자를 붙일 것
        String ext = originFileName.substring(originFileName.lastIndexOf("."));
        System.out.println("ext = " + ext);

        // 파일 사이에 있는 하이폰-을 없애버리는 작업
        String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
        System.out.println("savedName = " + savedName);

        // 결과:
        // originFileName = pikachu3.jpg
        // ext = .jpg
        // savedName = beeea04017ff458cbac5bed98d713368.jpg
        // 같은 이름으로 올려도 다른 이름으로 저장을 해놓게 됨!
        // 또한 원본 이름을 저장해놓으면 사용자가 다운을 받을 때 원본 이름을 돌려줄 건지 적용할 수 있음 - 원본 이름을 돌려주려면
        // 서버 측에서 부가적인 작업이 들어가는 것

        /* 6-4 */
        /* 설명. 우리가 지정한 경로로 파일을 저장하고,  */
        try {
            singleFile.transferTo(new File(filePath + "/" + savedName));

            /* 설명. DB를 다녀오는 BL 구문 작성(DB에 저장) */
            // BL = Business Logic
            /* 7-3 */
            /* 설명. BL이 성공하면 화면의 재료를 RedirectAttributes로 담는다.(flashAttribute로 담음) */
            rttr.addFlashAttribute("message", "파일 업로드 성공!");
            rttr.addFlashAttribute("img", "uploadFiles/img/single/" + savedName);
            rttr.addFlashAttribute("singleFileDescription", singleFileDescription);
            // flashAttribute 이기 때문에 새로고침 하면 사라짐!

        } catch (IOException e) {
            /* 설명. try 구문 안에서(DB를 다녀오는 business logic 처리) 예외가 발생하면 파일만 올라가면 안 되므로 찾아서 다시 지워줌 */
            new File(filePath + "/" + savedName).delete();
        }
        // 이렇게 던져주면 singleFile에 알아서 던져줌

        return "redirect:/result";
    }

    /* 8-1 */
    @PostMapping("multi-file")
    public String multiFileUpload(@RequestParam List<MultipartFile> multiFiles,
                                  @RequestParam String multiFileDescription,
                                  RedirectAttributes rttr) throws IOException {
//        System.out.println("multiFile = " + multiFile);
//        System.out.println("multiFileDescription = " + multiFileDescription);
        String filePath = resourceLoader.getResource("classpath:static/uploadFiles/img/multi")
                .getFile()
                .getAbsolutePath();


        /* 8-2 */
        /* 설명. 사용자가 올린 파일들을 다 rename하고 저장하는 작업 및 DB로 전달하기 위한 List에 쌓기 */
        // DTO 안 쓸 것!
        List<Map<String, String>> files = new ArrayList<>();        // DB에 저장할 값들을 지닌 List
        List<String> saveFiles = new ArrayList<>();                 // 화면단에서 img 태그가 참조할 정적 리소스 경로(src 경로)

        try {

            for (int i = 0; i < multiFiles.size(); i++) {
                String originFileName = multiFiles.get(i).getOriginalFilename();
                String ext = originFileName.substring(originFileName.lastIndexOf("."));
                String savedName = UUID.randomUUID().toString().replace("-", "") + ext;

                /* 설명. DB에 저장할 사용자가 올린 하나의 파일 정보(DB 테이블 컬럼을 참고할 것) */
                Map<String, String> file = new HashMap<>();
                file.put("originFileName", originFileName);
                file.put("savedName", savedName);
                file.put("filePath", filePath);

                files.add(file);

                multiFiles.get(i).transferTo(new File(filePath + "/" + savedName));
                saveFiles.add("uploadFiles/img/multi/" + savedName);


            }

            /* 설명. 여기까지 문제가 없었다면 화면의 재료를 던져준다. */
            rttr.addFlashAttribute("message", "다중 파일 업로드 성공!");
            rttr.addFlashAttribute("imgs", saveFiles);
            rttr.addFlashAttribute("multiFileDescription", multiFileDescription);

        } catch (Exception e) {

            /* 설명. List<Map<String, String>>으로 쌓인 업로드 된 파일들을 찾아 일일이 다시 지운다. */
            for (int i = 0; i < files.size(); i++) {
                Map<String, String> file = files.get(i);
                new File(filePath + "/" + file.get("savedName")).delete();
                // 예외가 발생하기 전에 반복이 돌고 있음
                // map 에 담고 나서 transfer to 로 파일에 담기는 과정
                // 그러다가 files.size만큼 돌면서
                // 성공한 개수만큼 쌓였을 거고
                // 하나씩 꺼내서 일치하면 지우는 작업을 해줌
            }

            /* 설명. 실패했다면 실패 메시지를 전달한다. */
            rttr.addFlashAttribute("message", "파일 업로드 실패!");
        }
        return "redirect:/result";
    }

    /* 7-2 */
    @GetMapping("result")
    public void result() {}
}
