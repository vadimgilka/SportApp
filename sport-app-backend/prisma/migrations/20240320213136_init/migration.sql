-- CreateTable
CREATE TABLE "trains" (
    "id" SERIAL NOT NULL,
    "name" VARCHAR(256) NOT NULL,
    "description" VARCHAR(1024) NOT NULL,
    "author_id" INTEGER NOT NULL,
    "createdAt" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updatedAt" TIMESTAMP(3) NOT NULL,

    CONSTRAINT "trains_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "exercises_trains" (
    "exerciseId" INTEGER NOT NULL,
    "trainId" INTEGER NOT NULL,
    "repetition" INTEGER,
    "approach" INTEGER NOT NULL DEFAULT 1,
    "time" INTEGER,
    "createdAt" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updatedAt" TIMESTAMP(3) NOT NULL,

    CONSTRAINT "exercises_trains_pkey" PRIMARY KEY ("exerciseId","trainId")
);

-- AddForeignKey
ALTER TABLE "trains" ADD CONSTRAINT "trains_author_id_fkey" FOREIGN KEY ("author_id") REFERENCES "users"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "exercises_trains" ADD CONSTRAINT "exercises_trains_exerciseId_fkey" FOREIGN KEY ("exerciseId") REFERENCES "exercises"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "exercises_trains" ADD CONSTRAINT "exercises_trains_trainId_fkey" FOREIGN KEY ("trainId") REFERENCES "trains"("id") ON DELETE RESTRICT ON UPDATE CASCADE;
