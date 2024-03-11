import { createParamDecorator, ExecutionContext } from '@nestjs/common'

export const User = createParamDecorator(
  function (data: unknown, ctx: ExecutionContext) {
    const req = ctx.switchToHttp().getRequest()

    return req.user ? req.user : null
}
)