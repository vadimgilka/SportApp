import { Optional } from "@nestjs/common";
import { BioType, Prisma } from "@prisma/client";
import { IsEnum, IsISO8601, IsInt, IsNotEmpty, IsOptional, IsString } from "class-validator";

export class CreateRemindDTO {

   
    @IsNotEmpty()
    @IsInt()
    biologicalAdditiveId : number;

    @IsNotEmpty()
    @IsInt()
    time : number;   


    @IsInt()         
    period : number;

    @IsString()
    @IsOptional()
    token? : string;    
}


export class UpdateRemindDTO {

    id : number;

    @IsNotEmpty()
    @IsInt()
    time : number;   


    @IsNotEmpty()
    @IsInt()
    biologicalAdditiveId : number;

    @IsInt()         
    period : number;

    @IsInt()
    measure : number;

    @IsInt()
    count_reception : number;

    @IsString()
    @IsOptional()
    token? : string;

}


export class CreateBioAdditiveRemindDTO {

    @IsNotEmpty()
    @IsInt()
    time : number;   


    @IsInt()         
    period : number;

    @IsString()
    @IsOptional()
    token? : string;    


    static toCreateInput(object: CreateBioAdditiveRemindDTO, userId: number) : Prisma.RemindCreateInput{

        return {... object, 
            User : {
                connect : {
                    id: userId
                }
            }
        }
    }
}