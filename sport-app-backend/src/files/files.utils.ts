
export class FileUtils {

  static createName(req, file, cb) {
    const uniquePrefix = Date.now() + '-' + Math.round(Math.random() * 1e9);
    const originalName = file.originalname;
    cb(null,  uniquePrefix + "-" + originalName);
  }

  static destinationImages(req, file, cb) {
    cb(null, './upload/images/');
  }

  static destinationGif(req, file, cb){
    cb(null, './upload/gif/');
  }
}
