/*
  Warnings:

  - The primary key for the `exercises_trains` table will be changed. If it partially fails, the table could be left without primary key constraint.
  - Added the required column `exerciseNumber` to the `exercises_trains` table without a default value. This is not possible if the table is not empty.

*/
-- AlterTable
ALTER TABLE "exercises_trains" DROP CONSTRAINT "exercises_trains_pkey",
ADD COLUMN     "exerciseNumber" INTEGER NOT NULL,
ADD CONSTRAINT "exercises_trains_pkey" PRIMARY KEY ("exerciseId", "trainId", "exerciseNumber");
