import { BadRequestException } from '@nestjs/common';
import { diskStorage } from 'multer';
import { FileUtils } from 'src/files/files.utils';

export const imageFileFilter = (req, file, callback) => {
  if (!file.originalname.match(/\.(jpg|jpeg|png|gif)$/)) {
    return callback(new BadRequestException('Only image files are allowed!'), false);
  }
  callback(null, true);
};

export const imageInterceptor = {
  name: 'image',
  maxCount: 1,
  storage: diskStorage({
    destination: FileUtils.destinationImages,
    filename: FileUtils.createName,
  }),
  fileFilter: imageFileFilter,
};
