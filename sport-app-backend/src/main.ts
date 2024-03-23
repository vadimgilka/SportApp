import { HttpAdapterHost, NestFactory } from '@nestjs/core';
import { AppModule } from './app/app.module';
import { HttpStatus, ValidationPipe } from '@nestjs/common';
import { PrismaClientExceptionFilter } from 'nestjs-prisma';
import { HttpExceptionFilter } from './utils/filters/httpexcepion.filter';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);
  const { httpAdapter } = app.get(HttpAdapterHost);

  const prismaFilter =   new PrismaClientExceptionFilter(httpAdapter, {
    P2000: HttpStatus.BAD_REQUEST,
    P2002: HttpStatus.CONFLICT,
    P2025: HttpStatus.NOT_FOUND,
  });

  app.useGlobalFilters(prismaFilter, new HttpExceptionFilter());
  app.useGlobalPipes(new ValidationPipe({stopAtFirstError: true}))
  await app.listen(3000);
}
bootstrap();
