<%@ page contentType="text/html;charset=UTF-8"%>

<!doctype html>
<html lang="zh">
<head>
    <title>upload</title>
    <meta charset="UTF-8">
</head>
<body>
    <h2>upload file</h2>
    <form method="post" action="./upload/" enctype="multipart/form-data">
        <div>
            <label for="file">请选择待上传的文件</label>
            <!-- multiple属性支持选中多个文件 -->
            <input type="file" id="file" name="file" multiple>
        </div>
        <div>
            <input type="submit" name="file">
        </div>
    </form>
</body>
</html>
