import { PipeTransform, Injectable, BadRequestException } from '@nestjs/common';
import { fileSize } from '../constants';

@Injectable()
export class ImagePipe implements PipeTransform {
  async transform(file: Express.Multer.File): Promise<Express.Multer.File> {
    const allowedTypes = ['image/png', 'image/jpeg', 'image/webp', 'image/gif'];
    const allowedSize = fileSize.image;
    if(!file) return null; 
    if (!allowedTypes.includes(file.mimetype)) {
      throw new BadRequestException(
        'Invalid file type. Only PNG, JPEG, GIF and WebP images are allowed.',
      );
    }

    if (file.size > allowedSize) {
      throw new BadRequestException('File size exceeds the limit (5MB).');
    }

    return file;
  }
}
