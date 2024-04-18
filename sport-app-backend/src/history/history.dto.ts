import { IsISO8601, IsInt, IsNumber} from "class-validator";

export class HistoryDto {
    
    authorId? : number;
    
    @IsInt()
    exerciseOnTrainId : number;

    @IsISO8601()
    createAt? : string;
}
