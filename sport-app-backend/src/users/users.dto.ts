import { Role } from '@prisma/client';
import { IsEmail, IsNotEmpty, Matches, MinLength } from 'class-validator';

export class CreateUserDto {

  @MinLength(4)
  @IsNotEmpty()
  username: string;

  @IsEmail()
  email: string;

  @IsNotEmpty()
  @MinLength(7)
  @Matches(/^[a-zA-Z0-9!@#$%^&*()-+=`~{}\[\]|:;"'<>,.?\\]{7,}$/, {
    message: 'Password is too weak',
  })
  password: string;
}
