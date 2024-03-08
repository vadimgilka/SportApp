import { Body, Controller, Get, Post, Request, UseGuards } from '@nestjs/common';
import { AuthGuard } from '@nestjs/passport';
import { LocalAuthGuard } from './authguard/local-auth.guard';
import { AuthService } from './auth.service';
import { JwtAuthGuard } from './authguard/jwt-auth.guard';
import { CreateUserDto } from 'src/users/users.dto';

@Controller('auth')
export class AuthController {

  constructor(private authService : AuthService) {}

  @UseGuards(LocalAuthGuard)
  @Post('login')
  async login(@Request() req) {
    return this.authService.login(req.user)
  }

  @Post('register')
  async register(@Body() user: CreateUserDto){
    return this.authService.register(user);
  }

  @UseGuards(JwtAuthGuard)
  @Get()
  async get(@Request() req) {
    console.log(req.user)
    return {element : 1, test : "test"}
  }
}
