import http from "../http-common";

class UploadFilesService {
    upload(file, onUploadProgress) {
        let formData = new FormData();

        formData.append("file", file);
        formData.append("user", "test");
        formData.append("password", "test");

        return http.post("/vpn", formData, {
            headers: {
                "Content-Type": "multipart/form-data",
            },
            onUploadProgress,
        });
    }

}

export default new UploadFilesService();