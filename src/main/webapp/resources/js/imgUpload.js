    UPLOADCARE_LOCALE = "ko"
    UPLOADCARE_LOCALE_TRANSLATIONS = {
        buttons: {
            choose: {
                files: {
                    other: 'upload!'
                }
            }
        }
    }

    uploadcare.Widget('[role=uploadcare-uploader]').onUploadComplete(function(info){
        document.querySelector('#uploadcare-url').value = JSON.stringify(info)
        document.querySelector('#memImg').src = info.cdnUrl;
    })