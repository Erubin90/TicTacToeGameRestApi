package io.ylab.ticTacToeGameRestApi.utils;

import io.ylab.ticTacToeGameRestApi.dto.StepDto;
import io.ylab.ticTacToeGameRestApi.exceptions.DontMachValueException;
import io.ylab.ticTacToeGameRestApi.utils.Check;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/*
 * Распознает 2 типа записей:
 * 1. Индекс - тип записи шага в которой шаг представляется в виде индекса массива или списка
 * 2. Координаты - тип записи шага в которой шаг представляется в виде двух чисел и первое число это строка, второе число колонка
 */
public class StepAdapter {
    
    private List<StepDto> stepList;
    
    private boolean isCoordinateTable;

    @Getter
    private int boardSize;

    private boolean isBeginsWithZero;

    public StepAdapter(List<StepDto> stepList) {
        this.stepList = new ArrayList<>();
        for (var step : stepList) {
            String move = step.getText();
            Check.isNullOrEmpty(move, "step num " + step.getNum());
            this.isCoordinateTable = move.matches("\\d+\\D+\\d+");
            if (this.isCoordinateTable) {
                String[] rowCol = move.split("\\D+");
                int row = Integer.parseInt(rowCol[0]);
                int col = Integer.parseInt(rowCol[rowCol.length - 1]);
                this.boardSize = Math.max(Math.max(row, col), this.boardSize);
            }
            else {
                int intMove = Integer.parseInt(move);
                this.boardSize = Math.max((int) Math.sqrt(intMove), boardSize);
            }

            //Определяет начинается отчет с 0
            if(move.matches("0.*"))
                this.isBeginsWithZero = true;
            this.stepList.add(step);
        }
    }
    
    public List<StepDto> adaptStepList()  {
        int row;
        int col;

        if (this.isBeginsWithZero)
            this.boardSize++;

        int maxValue = boardSize * boardSize;

        for (var step : this.stepList) {
            var move = step.getText();
            Check.isNullOrEmpty(move, "step num " + step.getNum());
            if (this.isCoordinateTable) {
                String[] rowCol = move.split("\\D+");
                row = Integer.parseInt(rowCol[0]);
                col = Integer.parseInt(rowCol[rowCol.length - 1]);
                if (!this.isBeginsWithZero) {
                    row--;
                    col--;
                }
            }
            else {
                int index = Integer.parseInt(move);
                if (index <= maxValue) {
                    if (!this.isBeginsWithZero)
                        index--;
                    row = index / boardSize;
                    col = index - row * boardSize;
                }
                else {
                    throw new DontMachValueException("The current move value should not be greater than MaxValue. maxValue - "
                            + maxValue + ", move - " + index);
                }
            }
            step.setRow(row);
            step.setColumn(col);
        }
        return stepList;
    }
}