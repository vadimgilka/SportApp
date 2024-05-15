import { Prisma } from '@prisma/client';
import logger from './logger';
import { PrismaClientKnownRequestError } from '@prisma/client/runtime/library';

export function prismaLoggingMiddleware(): Prisma.Middleware {
  return async (
    params: Prisma.MiddlewareParams,
    next: (params: Prisma.MiddlewareParams) => Promise<any>,
  ) => {
    const before = Date.now();

    try {
      const result = await next(params);

      const after = Date.now();
      const logMessage = `Query ${params.model}.${params.action} took ${after - before}ms`;
      logger.info(logMessage);

      return result;
    } catch (error) {
      if (error instanceof PrismaClientKnownRequestError) {
        logger.error(`Prisma error: ${error.message}`);
        throw error;
      } else {
        logger.error(`Unexpected error: ${error}`);
        throw error;
      }
    }
  };
}
