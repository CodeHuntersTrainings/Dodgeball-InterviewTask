package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DodgeBallTest {

    @Test
    void testByExampleJsonInput1() throws Exception {
        //Given
        String input = new String(DodgeBallTest.class.getClassLoader()
                .getResourceAsStream("json_inputs/input1.json")
                .readAllBytes());


        DodgeBall underTest = new DodgeBall();

        //When
        String output = underTest.playAllGamesFromJsonInput(input);

        //Then
        String expectedOutput = """
                4 8
                5 6
                """;

        Assertions.assertEquals(expectedOutput.trim(), output.trim());
    }

    @Test
    void testByExampleJsonInput2() throws Exception {
        //Given
        String input = new String(DodgeBallTest.class.getClassLoader()
                .getResourceAsStream("json_inputs/input2.json")
                .readAllBytes());

        DodgeBall underTest = new DodgeBall();

        //When
        String output = underTest.playAllGamesFromJsonInput(input);

        //Then
        String expectedOutput = """
                493 321
                448 398
                421 395
                487 370
                483 496
                479 35
                486 13
                444 449
                497 302
                404 255
                499 201
                477 374
                479 320
                421 311
                468 441
                452 425
                473 14
                436 312
                471 309
                441 401
                384 363
                417 129
                439 88
                486 158
                474 391
                466 257
                433 33
                447 351
                423 313
                480 7
                456 214
                245 98
                488 276
                484 479
                499 319
                434 305
                420 470
                413 149
                284 394
                481 486
                396 348
                477 310
                425 4
                483 454
                456 299
                467 269
                166 342
                436 88
                444 325
                203 456
                475 439
                384 89
                498 44
                465 123
                476 309
                446 51
                418 199
                454 212
                418 236
                413 340
                498 279
                466 118
                478 122
                428 70
                423 292
                475 9
                454 292
                118 260
                443 370
                474 158
                425 442
                301 378
                492 124
                479 430
                417 178
                465 303
                395 230
                449 2
                363 387
                495 87
                450 283
                459 473
                497 359
                463 42
                219 72
                316 183
                482 379
                497 49
                486 271
                475 238
                437 147
                490 89
                440 5
                445 375
                382 330
                496 71
                460 173
                135 378
                466 418
                361 58
                """;

        Assertions.assertEquals(expectedOutput.trim(), output.trim());
    }

    @Test
    void testByExampleTxtInput1() throws Exception {
        //Given
        String input = new String(DodgeBallTest.class.getClassLoader()
                .getResourceAsStream("txt_inputs/input1.txt")
                .readAllBytes());


        DodgeBall underTest = new DodgeBall();

        //When
        String output = underTest.playAllGamesFromTxtInput(input);

        //Then
        String expectedOutput = """
                4 8
                5 6
                """;

        Assertions.assertEquals(expectedOutput.trim(), output.trim());
    }

    @Test
    void testByExampleTxtInput2() throws Exception {
        //Given
        String input = new String(DodgeBallTest.class.getClassLoader()
                .getResourceAsStream("txt_inputs/input2.txt")
                .readAllBytes());

        DodgeBall underTest = new DodgeBall();

        //When
        String output = underTest.playAllGamesFromTxtInput(input);

        //Then
        String expectedOutput = """
                493 321
                448 398
                421 395
                487 370
                483 496
                479 35
                486 13
                444 449
                497 302
                404 255
                499 201
                477 374
                479 320
                421 311
                468 441
                452 425
                473 14
                436 312
                471 309
                441 401
                384 363
                417 129
                439 88
                486 158
                474 391
                466 257
                433 33
                447 351
                423 313
                480 7
                456 214
                245 98
                488 276
                484 479
                499 319
                434 305
                420 470
                413 149
                284 394
                481 486
                396 348
                477 310
                425 4
                483 454
                456 299
                467 269
                166 342
                436 88
                444 325
                203 456
                475 439
                384 89
                498 44
                465 123
                476 309
                446 51
                418 199
                454 212
                418 236
                413 340
                498 279
                466 118
                478 122
                428 70
                423 292
                475 9
                454 292
                118 260
                443 370
                474 158
                425 442
                301 378
                492 124
                479 430
                417 178
                465 303
                395 230
                449 2
                363 387
                495 87
                450 283
                459 473
                497 359
                463 42
                219 72
                316 183
                482 379
                497 49
                486 271
                475 238
                437 147
                490 89
                440 5
                445 375
                382 330
                496 71
                460 173
                135 378
                466 418
                361 58
                """;

        Assertions.assertEquals(expectedOutput.trim(), output.trim());
    }

    @Test
    void testByExampleChallengeTxtInput() throws Exception {
        //Given
        String input = new String(DodgeBallTest.class.getClassLoader()
                .getResourceAsStream("txt_inputs/input0.txt")
                .readAllBytes());


        DodgeBall underTest = new DodgeBall();

        //When
        String output = underTest.playAllGamesFromTxtInput(input);

        //Then
        System.out.println(output);
    }

    @Test
    void testByExampleChallengeJsonInput() throws Exception {
        //Given
        String input = new String(DodgeBallTest.class.getClassLoader()
                .getResourceAsStream("json_inputs/input0.json")
                .readAllBytes());


        DodgeBall underTest = new DodgeBall();

        //When
        String output = underTest.playAllGamesFromJsonInput(input);

        //Then
        System.out.println(output);
    }

}