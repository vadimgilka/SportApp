import { Catch, ExceptionFilter, ArgumentsHost, HttpException, HttpStatus } from '@nestjs/common';
import { Request, Response } from 'express';
import logger from 'src/prisma/logger';

@Catch(HttpException)
export class HttpExceptionFilter implements ExceptionFilter {
  catch(exception: HttpException, host: ArgumentsHost) {
    const ctx = host.switchToHttp();
    const response = ctx.getResponse<Response>();
    const request = ctx.getRequest<Request>();
    const status = exception.getStatus();

    if(exception.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR){
      //console.log("FFFFFFF")
      logger.error(exception.stack);
    }
    
    if(exception.getStatus() == HttpStatus.BAD_REQUEST){
      logger.error(exception.message);
    }

    response
      .status(status)
      .json({
        statusCode: status,
        message: exception.message,
        timestamp: new Date().toISOString(),
        path: request.url,
      });
  }
}