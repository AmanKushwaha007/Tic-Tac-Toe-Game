package com.cupid.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    int playerTurnCount = 0;
    int Winner=-2;

    // Checking Where we could place next move by computer
    int[] position = new int[2];
    int[][] matrix = new int[3][3];
    int[][] startPick = {{0,0}, {0,2}, {1,1}, {2,0}, {2,2}, {0,1}, {1,0}, {1,2}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_main);
    }

    public void b_start(View view)
    {
        setContentView(R.layout.game_activity);
        matrix = new int[3][3];
        position = new int[2];
        playerTurnCount = 0;
        Winner = -2;
    }
    public void b_setting(View view)
    {

    }
    public void b_quit(View view)
    {
        finish();
        System.exit(0);
    }

    public void b_exit(View view)
    {
        setContentView(R.layout.acivity_main);
    }


    public void b00(View view)
    {
        playerInput(0,0);
    }
    public void b01(View view)
    {
        playerInput(0,1);
    }
    public void b02(View view)
    {
        playerInput(0,2);
    }
    public void b10(View view)
    {
        playerInput(1,0);
    }
    public void b11(View view)
    {
        playerInput(1,1);
    }
    public void b12(View view)
    {
        playerInput(1,2);
    }
    public void b20(View view)
    {
        playerInput(2,0);
    }
    public void b21(View view)
    {
        playerInput(2,1);
    }
    public void b22(View view)
    {
        playerInput(2,2);
    }

    public void ChangeMatrix(int x, int y, int isplayer)
    {
        if(isplayer==1)matrix[x][y] = 1;
        else matrix[x][y] = 2;
    }
    public void AITurn()
    {
        if(playerTurnCount == 1)
        {
            Random rand = new Random();
            int[] position = startPick[rand.nextInt(startPick.length)];

            // For making sure it do its task over zero not over player inputs
            for(int i = 0; i < 9; i++)
            {
                if(matrix[position[0]][position[1]] == 0)
                {
                    break;
                }
                else
                {
                    position = startPick[rand.nextInt(startPick.length)];
                }
            }
            ChangeMatrix(position[0], position[1], 0);
        }
        else if(playerTurnCount == 2)
        {
            int flag = 0;
            if(RowChecking(1) == 1)
            {
                if(matrix[position[0]][position[1]] != 0)
                {
                    flag = 1;
                }else ChangeMatrix(position[0], position[1], 2);
            }
            else if(ColounmChecking(1) == 1)
            {
                if(matrix[position[0]][position[1]] != 0)
                {
                    flag = 1;
                }else ChangeMatrix(position[0], position[1], 2);
            }
            else if(DiagonalChecking(1) == 1)
            {
                if(matrix[position[0]][position[1]] != 0)
                {
                    flag = 1;
                }else ChangeMatrix(position[0], position[1], 2);
            }
            if (flag == 1)
            {
                Random rand = new Random();
                int[] position = new int[2];

                // For making sure it do its task over zero not over player inputs
                // And it could dumb because i do not have much to finish it right now
                // i will make it will completed after some time
                for(int i = 0; i <3; i++)
                {
                    for(int j = 0; j <3; j++)
                    {
                        if (matrix[i][j] == 0) {
                            position[0] = i;
                            position[1] = j;
                            break;
                        }
                    }
                }
                ChangeMatrix(position[0], position[1], 2);
            }
        }
        else if(playerTurnCount >= 3)
        {
            int status = CheckWin(1);
            if(status == 1)
            {
                Winner = 1;
            }
            else
            {
                int flag = 0;
                if(RowChecking(1) == 1)
                {
                    if(matrix[position[0]][position[1]] != 0)
                    {
                        flag = 1;
                    }else ChangeMatrix(position[0], position[1], 2);
                }
                else if(ColounmChecking(1) == 1)
                {
                    if(matrix[position[0]][position[1]] != 0)
                    {
                        flag = 1;
                    }else ChangeMatrix(position[0], position[1], 2);
                }
                else if(DiagonalChecking(1) == 1)
                {
                    if(matrix[position[0]][position[1]] != 0)
                    {
                        flag = 1;
                    }else ChangeMatrix(position[0], position[1], 2);
                }
                if(flag == 1)
                {
                    Random rand = new Random();
                    int[] position = new int[2];

                    // For making sure it do its task over zero not over player inputs
                    // And it could dumb because i do not have much to finish it right now
                    // i will make it will completed after some time
                    for(int i = 0; i <3; i++)
                    {
                        for(int j = 0; j <3; j++)
                        {
                            if (matrix[i][j] == 0) {
                                position[0] = i;
                                position[1] = j;
                                break;
                            }
                        }
                    }
                    ChangeMatrix(position[0], position[1], 2);
                }
            }

            if(CheckWin(2) == 2)
            {
                Winner = 2;
            }
            if(CheckWin(1) == 1)
            {
                Winner = 1;
            }
            else
            {
                Winner = 0;
            }
        }
        else if(playerTurnCount >= 5)
        {
            if(CheckWin(2) == 2)
            {
                Winner = 2;
            }
            if(CheckWin(1) == 1)
            {
                Winner = 1;
            }
            else
            {
                Winner = 0;
            }
        }
    }


    public void playerInput(int x, int y)
    {
        if(matrix[x][y] != 0) return;
        playerTurnCount++;
        ChangeMatrix(x,y, 1);
        AITurn();
        ChangeBoard();

        if(Winner == -1)
        {
            setContentView(R.layout.acivity_main);
            Toast.makeText(this, "Match is Draw.",Toast.LENGTH_LONG).show();
        }
        else if(Winner == 1)
        {
            setContentView(R.layout.acivity_main);
            Toast.makeText(this, "You Won the last match.",Toast.LENGTH_LONG).show();
        }
        else if(Winner == 2)
        {
            setContentView(R.layout.acivity_main);
            Toast.makeText(this, "Computer Won the last match.",Toast.LENGTH_LONG).show();
        }
        else if(Winner == 0)
        {
            if(CheckSpace() == 0)
            {
                setContentView(R.layout.acivity_main);
                Toast.makeText(this, "Match Draw.",Toast.LENGTH_LONG).show();
            }

        }
    }
    public void ChangeBoard()
    {
        int imagePlace=0;

        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                imagePlace++;
                if(matrix[i][j] == 1)
                {
                    enableImage(imagePlace, 1);
                }
                else if(matrix[i][j] == 2)
                {
                    enableImage(imagePlace, 2);
                }
                else
                {
                    enableImage(imagePlace, 0);
                }
            }
        }
    }
    public void enableImage(int place, int isPlayer)
    {
        ImageView i1, i2, i3, i4, i5, i6, i7, i8, i9;
        i1 = findViewById(R.id.i1);
        i2 = findViewById(R.id.i2);
        i3 = findViewById(R.id.i3);
        i4 = findViewById(R.id.i4);
        i5 = findViewById(R.id.i5);
        i6 = findViewById(R.id.i6);
        i7 = findViewById(R.id.i7);
        i8 = findViewById(R.id.i8);
        i9 = findViewById(R.id.i9);

        if(isPlayer == 1) {
            switch (place) {
                case 1:
                    i1.setImageResource(R.drawable.cross);
                    break;
                case 2:
                    i2.setImageResource(R.drawable.cross);
                    break;
                case 3:
                    i3.setImageResource(R.drawable.cross);
                    break;
                case 4:
                    i4.setImageResource(R.drawable.cross);
                    break;
                case 5:
                    i5.setImageResource(R.drawable.cross);
                    break;
                case 6:
                    i6.setImageResource(R.drawable.cross);
                    break;
                case 7:
                    i7.setImageResource(R.drawable.cross);
                    break;
                case 8:
                    i8.setImageResource(R.drawable.cross);
                    break;
                case 9:
                    i9.setImageResource(R.drawable.cross);
                    break;
                default:
                    break;
            }
        }
        else if(isPlayer == 2)
        {
            switch (place) {
                case 1:
                    i1.setImageResource(R.drawable.o);
                    break;
                case 2:
                    i2.setImageResource(R.drawable.o);
                    break;
                case 3:
                    i3.setImageResource(R.drawable.o);
                    break;
                case 4:
                    i4.setImageResource(R.drawable.o);
                    break;
                case 5:
                    i5.setImageResource(R.drawable.o);
                    break;
                case 6:
                    i6.setImageResource(R.drawable.o);
                    break;
                case 7:
                    i7.setImageResource(R.drawable.o);
                    break;
                case 8:
                    i8.setImageResource(R.drawable.o);
                    break;
                case 9:
                    i9.setImageResource(R.drawable.o);
                    break;
                default:
                    break;
            }
        }
        else
        {
            switch (place) {
                    case 1:
                        i1.setImageResource(0);
                        break;
                    case 2:
                        i2.setImageResource(0);
                        break;
                    case 3:
                        i3.setImageResource(0);
                        break;
                    case 4:
                        i4.setImageResource(0);
                        break;
                    case 5:
                        i5.setImageResource(0);
                        break;
                    case 6:
                        i6.setImageResource(0);
                        break;
                    case 7:
                        i7.setImageResource(0);
                        break;
                    case 8:
                        i8.setImageResource(0);
                        break;
                    case 9:
                        i9.setImageResource(0);
                        break;
                    default:
                        break;
                }
        }
    }

    public int RowChecking(int isPlayer)
    {
        int count;
        for(int i = 0; i < 3; i++)
        {
            position[0] = i; position[1]= 0;
            count = 0;

            for(int j = 0; j < 3; j++)
            {
                if(matrix[i][j] == isPlayer)
                {
                    count++;
                }
                if(count == 2)
                {
                    if(j==1)
                    {
                        position[1]= j+1;
                    }
                    else if(j == 2)
                    {
                        if(matrix[i][j-1] == 0)position[1]= j-1;
                    }
                    return 1;
                }
            }

        }
        return 0;
    }

    public int ColounmChecking(int isPlayer)
    {
        int count;
        for(int i = 0; i < 3; i++)
        {
            position[0] = 0; position[1]= i;
            count = 0;

            for(int j = 0; j < 3; j++)
            {
                if(matrix[j][i] == isPlayer)
                {
                    count++;
                }
                if(count == 2)
                {

                    if(j==1)
                    {
                        position[0]= j+1;
                    }
                    else if(j==2)
                    {
                        if(matrix[i][j-1] == 0)position[0]= j-1;
                    }
                    return 1;
                }
            }

        }
        return 0;
    }

    public int DiagonalChecking(int isPlayer)
    {
        if(isPlayer == 1) {
            if (matrix[1][1] == 1) {
                if (matrix[0][0] == 1) {
                    position[0] = 2;
                    position[1] = 2;
                    return 1;
                } else if (matrix[0][2] == 1) {
                    position[0] = 2;
                    position[1] = 0;
                    return 1;
                } else if (matrix[2][2] == 1) {
                    position[0] = 0;
                    position[1] = 0;
                    return 1;
                } else if (matrix[2][0] == 1) {
                    position[0] = 0;
                    position[1] = 2;
                    return 1;
                }
            }
        }
        else
        {
            if (matrix[1][1] == 2) {
                if (matrix[0][0] == 2) {
                    position[0] = 2;
                    position[1] = 2;
                    return 1;
                } else if (matrix[0][2] == 2) {
                    position[0] = 2;
                    position[1] = 0;
                    return 1;
                } else if (matrix[2][2] == 2) {
                    position[0] = 0;
                    position[1] = 0;
                    return 1;
                } else if (matrix[2][0] == 2) {
                    position[0] = 0;
                    position[1] = 2;
                    return 1;
                }
            }
        }
        return 0;
    }

    public int CheckWin(int isPlayer)
    {
        //Winner = 1 when player and = 2 when computer and -1 if no one
        int Winner = -1;


        if(isPlayer == 1)
        {
            // Cheacking Player Win
            if(Check_Row(1) == 1)
            {
                Winner = 1;
            }
            else if(Check_Colomn(1) == 1)
            {
                Winner = 1;
            }
            else if(Check_Diagonal(1) == 1)
            {
                Winner = 1;
            }
        }

        else if(isPlayer == 2)
        {
            // Cheacking ComuterWin
            if(Check_Row(2) == 1)
            {
                    Winner = 2;
            }
            else if(Check_Colomn(2) == 1)
            {
                Winner = 2;
            }
            else if(Check_Diagonal(2) == 1)
            {
                Winner = 2;
            }
        }


        return Winner;
    }


    public int CheckSpace()
    {
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++) {
                if (matrix[i][j] == 0)
                {
                    return 1;
                }
            }
        }
        return 0;
    }

    public int Check_Row(int isPlayer)
    {
        int count;
        for(int i = 0; i < 3; i++)
        {
            position[0] = i; position[1]= 0;
            count = 0;

            for(int j = 0; j < 3; j++)
            {
                if(matrix[i][j] == isPlayer)
                {
                    count++;
                }
                if(count == 3)
                {
                    return 1;
                }
            }

        }
        return 0;
    }
    public int Check_Colomn(int isPlayer)
    {
        int count;
        for(int i = 0; i < 3; i++)
        {
            position[0] = 0; position[1]= i;
            count = 0;

            for(int j = 0; j < 3; j++)
            {
                if(matrix[j][i] == isPlayer)
                {
                    count++;
                }
                if(count == 3)
                {
                    return 1;
                }
            }

        }
        return 0;
    }
    public int Check_Diagonal(int isPlayer)
    {
        if(isPlayer == 1 )
        {
            if (matrix[1][1] == 1) {
                if (matrix[0][0] == 1 && matrix[2][2] == 1 ) {
                    return 1;
                } else if (matrix[0][2] == 1 && matrix[2][0] == 1) {
                    return 1;
                } else if (matrix[2][2] == 1 && matrix[0][0] == 1) {
                    return 1;
                } else if (matrix[2][0] == 1 && matrix[0][2] == 1) {
                    return 1;
                }
            }
        }
        else
        {
            if (matrix[1][1] == 1) {
                if (matrix[0][0] == 2 && matrix[2][2] == 2) {
                    return 1;
                } else if (matrix[0][2] == 2 && matrix[2][0] == 2) {
                    return 1;
                } else if (matrix[2][2] == 2 && matrix[0][0] == 2) {
                    return 1;
                } else if (matrix[2][0] == 2 && matrix[0][2] == 2) {
                    return 1;
                }
            }
        }
        return 0;
    }
}