package com.example.user.a2_texttospeechexam.helper;

import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class FileHelper {
    // ---------- 싱글톤 객체 생성 시작 ------------
    private static FileHelper instance = null;

    public static FileHelper getInstance() {
        if (instance == null) instance = new FileHelper();   // null일 때만 딱 한번 객체 생성

        return instance;
    }

    private FileHelper() {
    }
    // ---------- 싱글톤 객체 생성 끝 ------------

    /**
     * 주어진 경로에 data 값을 기록하고 저장한다.
     *
     * @param filePath - 저장할 파일 경로
     * @param data     - 저장할 내용을 담은 스트림
     * @return boolean - 성공시 true, 실패시 false
     */
    public boolean write(String filePath, byte[] data) throws FileNotFoundException {
        boolean result = false;
        /** 파일 저장 절차 시작 */
        // finally 블록에서 인식하기 위해서 선언부를 위로 이동시킨다.
        OutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
            // 파일쓰기
            out.write(data);
            // 저장에 성공하였으므로, 결과값을 true로 변경
            result = true;
            Log.d("[INFO]", "파일 저장 성공" + filePath);
        } catch (FileNotFoundException e) {
            Log.d("[ERROR]", "지정된 경로를 찾을 수 없음 >> "+filePath);
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("[ERROR]", "파일 저장 실패" + filePath);
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("[ERROR]", "알 수 없는 에러 >> " + filePath);
            e.printStackTrace();
        } finally {
            try {
                if(out != null) out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 주어진 경로의 파일을 읽고, 내용을 스트림으로 리턴한다.
     * @param filePath - 읽어야 할 파일의 경로
     * @return 읽혀진 내용
     */

    public byte[] read(String filePath) {
        byte[] data = null;
        InputStream in = null;
        try {
            in = new FileInputStream(filePath);
            // 읽은 내용을 담기 위한 배열은 파일의 용량만큼 사이즈를 할당한다.
            // in.available() -> 열고 있는 파일의 크기
            data = new byte[in.available()];
            // 파일 읽기 - 파라미터로 전달된 배열 안에, 파일의 내용을 담아준다.
            in.read(data);
            Log.d("[INFO]", "파일 저장 성공 >> " + filePath);
        } catch (FileNotFoundException e) {
            Log.d("[ERROR]","지정된 경로를 찾을 수 없음 >> " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("[ERROR]","파일 읽기 실패 >> " + filePath);
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("[ERROR]","알 수 없는 에러 >> " + filePath);
            e.printStackTrace();
        } finally {
            try {
                if(in != null) in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data; // byte 배열 리턴
    }

    /**
     * 파일을 저장한다.
     * @param filePath - 저장할 파일 경로
     * @param content - 저장할 내용
     * @param encType - 인코딩 형식
     * @return boolean - 성공시 true, 실패시 false
     */
    public boolean writeString(String filePath, String content, String encType) {
        boolean result = false;
        byte[] data = null;
        try {
            data = content.getBytes(encType);
            result = write(filePath, data);
        } catch (UnsupportedEncodingException e) {
            Log.d("[ERROR]","인코딩 지정 에러");
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("[ERROR]", "알 수 없는 에러");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 파일의 내용을 문자열로 리턴한다.
     * @param filePath - 읽어야 할 파일의 경로
     * @param encType - 인코딩 형식
     * @return String - 읽은 내용에 대한 문자열
     */
    public String readString(String filePath, String encType) {
        String content = null;
        byte[] data = read(filePath);
        // 내용을 문자열로 반환한다.
        try {
            content = new String(data, encType);
        } catch (UnsupportedEncodingException e) {
            Log.d("[ERROR]","인코딩 지정 에러");
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("[ERROR]","알 수 없는 에러");
            e.printStackTrace();
        }
        return content;
    }
}