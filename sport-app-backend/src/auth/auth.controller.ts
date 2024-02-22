import { Controller, Get, Post, Request, UseGuards } from '@nestjs/common';
import { AuthGuard } from '@nestjs/passport';
import { LocalAuthGuard } from './authguard/local-auth.guard';
import { AuthService } from './auth.service';
import { JwtAuthGuard } from './authguard/jwt-auth.guard';

@Controller('auth')
export class AuthController {

  constructor(private authService : AuthService) {}

  @UseGuards(LocalAuthGuard)
  @Post('login')
  async login(@Request() req) {
    return this.authService.login(req.user)
  }

  @UseGuards(JwtAuthGuard)
  @Get()
  async get(@Request() req) {
    return {element : 1, test : "test"}
  }
}
