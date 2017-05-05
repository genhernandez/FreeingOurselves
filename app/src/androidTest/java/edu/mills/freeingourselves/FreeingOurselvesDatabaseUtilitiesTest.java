package edu.mills.freeingourselves;

import android.content.Context;
import android.database.Cursor;
import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FreeingOurselvesDatabaseUtilitiesTest {

    private Context context = InstrumentationRegistry.getTargetContext();
    private static final String HOME = "Home";
    private static final String TESTOSTERONE = "Testosterone and You";
    private static final String ABOUT_US = "About us";
    private static final String ABOUT = "about";
    private static final String BROWN_BOI = "Brown Boi Project - Our organization";
    private static final String BROWN_BOI_LINK = "http://www.brownboiproject.org/";
    private static final String BICEP_CURL = "Alternating Bicep Curl: 8-12 reps";
    private static final String SIDE_BENDS = "Side Bends: 15-25 reps (each side)";
    private static final String SIDE_BENDS_INFO = "Hold weights in each hand. Shift the weight " +
            "downwards on one side then pull up. Repeat on other side.";
    private static final String SERVICES_QUESTION = "What services does your office/clinic offer" +
            " to young people? What ongoing services do you offer to young people as they " +
            "transition into adulthood?";
    private static final String REGISTRATION_QUESTION = "What is the registration/scheduling" +
            " process and what are your hours of operation?";
    private static final String TEST_NOTES = "test notes";



//    @Test
//    public void updateTopicsFavorite() throws Exception {
//        boolean success = FreeingOurselvesDatabaseUtilities.updateTopicsFavorite(context, 1, true);
//        assertEquals(true, success);
//        Cursor topicCursor = FreeingOurselvesDatabaseUtilities.getSpecificTopic(context, 1);
//        topicCursor.moveToFirst();
//        assertEquals(1, topicCursor.getInt(2));
//
//        success = FreeingOurselvesDatabaseUtilities.updateTopicsFavorite(context, 1, false);
//        assertEquals(true, success);
//        topicCursor = FreeingOurselvesDatabaseUtilities.getSpecificTopic(context, 1);
//        topicCursor.moveToFirst();
//        assertEquals(0, topicCursor.getInt(2));
//    }
//
//    @Test
//    public void getFaveTopics() throws Exception {
//        FreeingOurselvesDatabaseUtilities.updateTopicsFavorite(context, 1, true);
//        Cursor topicCursor = FreeingOurselvesDatabaseUtilities.getFaveTopics(context);
//        if (topicCursor != null) {
//            topicCursor.moveToFirst();
//            assertEquals(1, topicCursor.getInt(0));
//            assertEquals("Home", topicCursor.getString(1));
//        } else {
//            Log.d("getFaveTopics test", "oh no");
//        }
//    }

    @Test
    public void getResources() throws Exception {
        ArrayList<String> resources = FreeingOurselvesDatabaseUtilities.getResources(context);
        if (resources != null) {
            assertEquals(BROWN_BOI, resources.get(0));
        } else {
            fail();
        }
    }

    @Test
    public void getResourceLink() throws Exception {
        String link = FreeingOurselvesDatabaseUtilities.getResourceLink(context, 0);
        assertEquals(BROWN_BOI_LINK, link);
    }

    @Test
    public void getWorkoutNames() throws Exception {
        ArrayList<String> workouts = FreeingOurselvesDatabaseUtilities.getWorkoutNames(context);
        if (workouts != null) {
            assertEquals(BICEP_CURL, workouts.get(0));
//            assertEquals("Lying-Down Tricep Extension: 8-12 reps", workouts.get(1));
//            assertEquals("V-Ups: 15-25 reps", workouts.get(2));
        } else {
            fail();
        }
    }

//    @Test
//    public void getWorkouts() throws Exception {
//
//    }

    @Test
    public void getSpecificWorkout() throws Exception {
        Cursor workoutCursor = FreeingOurselvesDatabaseUtilities.getSpecificWorkout(context, 6);
        if (workoutCursor != null) {
            workoutCursor.moveToFirst();
            assertEquals(SIDE_BENDS, workoutCursor.getString(0));
            assertEquals(SIDE_BENDS_INFO, workoutCursor.getString(1));
        } else {
            fail();
        }
    }

    @Test
    public void updateWorkoutFavorite() throws Exception {
        boolean success = FreeingOurselvesDatabaseUtilities.updateWorkoutFavorite(context, 1, true);
        assertEquals(true, success);
        Cursor workoutCursor = FreeingOurselvesDatabaseUtilities.getSpecificWorkout(context, 1);
        if (workoutCursor != null) {
            workoutCursor.moveToFirst();
            assertEquals(1, workoutCursor.getInt(4));
        } else {
            fail();
        }

        success = FreeingOurselvesDatabaseUtilities.updateWorkoutFavorite(context, 1, false);
        assertEquals(true, success);
        workoutCursor = FreeingOurselvesDatabaseUtilities.getSpecificWorkout(context, 1);
        if (workoutCursor != null) {
            workoutCursor.moveToFirst();
            assertEquals(0, workoutCursor.getInt(4));
        } else {
            fail();
        }
    }

    @Test
    public void updateWorkoutCount() throws Exception {
        Cursor cursor1 = FreeingOurselvesDatabaseUtilities.getSpecificWorkout(context, 1);
        if (cursor1 != null) {
            cursor1.moveToFirst();
            int initialCount = cursor1.getInt(3);

            boolean success = FreeingOurselvesDatabaseUtilities.updateWorkoutCount(context, 1);
            assertEquals(true, success);

            Cursor cursor2 = FreeingOurselvesDatabaseUtilities.getSpecificWorkout(context, 1);
            if (cursor2 != null) {
                cursor2.moveToFirst();
                assertEquals(++initialCount, cursor2.getInt(3));
            } else {
                fail();
            }
        } else {
            fail();
        }
    }

    @Test
    public void getFaveWorkouts() throws Exception {
        FreeingOurselvesDatabaseUtilities.updateWorkoutFavorite(context, 1, true);
        Cursor workoutCursor = FreeingOurselvesDatabaseUtilities.getFaveWorkouts(context);
        if (workoutCursor != null) {
            workoutCursor.moveToFirst();
            assertEquals(1, workoutCursor.getInt(0));
            assertEquals(BICEP_CURL, workoutCursor.getString(1));
        } else {
            fail();
        }
    }

//    @Test
//    public void getHealthcareInfo() throws Exception {
//
//    }

    @Test
    public void getHealthCareQuestions() throws Exception {
        ArrayList<String> questions = FreeingOurselvesDatabaseUtilities.getHealthCareQuestions(context);
        if (questions != null) {
            assertEquals(SERVICES_QUESTION, questions.get(0));
//            assertEquals("Are there specific documents required to get care from your office (age" +
//                    " range, citizenship status, insurance coverage, parental consent, etc.)?", questions.get(1));
        } else {
            fail();
        }
    }

    @Test
    public void getSpecificHealthcareQuestion() throws Exception {
        Cursor questionCursor = FreeingOurselvesDatabaseUtilities.getSpecificHealthcareQuestion(context, 3);
        if (questionCursor != null) {
            questionCursor.moveToFirst();
            assertEquals(REGISTRATION_QUESTION, questionCursor.getString(0));
            assertEquals(null, questionCursor.getString(1));
            assertEquals(0, questionCursor.getInt(1));
        } else {
            fail();
        }
    }

    @Test
    public void updateNotes() throws Exception {
        boolean success = FreeingOurselvesDatabaseUtilities.updateNotes(context, 1, TEST_NOTES);
        assertEquals(true, success);
        Cursor questionCursor = FreeingOurselvesDatabaseUtilities.getSpecificHealthcareQuestion(context, 1);
        if (questionCursor != null) {
            questionCursor.moveToFirst();
            assertEquals(TEST_NOTES, questionCursor.getString(1));
        } else {
            fail();
        }
    }

    @Test
    public void updateSaved() throws Exception {
        boolean success = FreeingOurselvesDatabaseUtilities.updateSaved(context, 1, true);
        assertEquals(true, success);
        Cursor questionCursor = FreeingOurselvesDatabaseUtilities.getSpecificHealthcareQuestion(context, 1);
        if (questionCursor != null) {
            questionCursor.moveToFirst();
            assertEquals(1, questionCursor.getInt(2));
        } else {
            fail();
        }

        success = FreeingOurselvesDatabaseUtilities.updateSaved(context, 1, false);
        assertEquals(true, success);
        questionCursor = FreeingOurselvesDatabaseUtilities.getSpecificHealthcareQuestion(context, 1);
        if (questionCursor != null) {
            questionCursor.moveToFirst();
            assertEquals(0, questionCursor.getInt(2));
        } else {
            fail();
        }
    }

    @Test
    public void getSavedHealthcare() throws Exception {
        FreeingOurselvesDatabaseUtilities.updateSaved(context, 1, true);
        Cursor healthcareCursor = FreeingOurselvesDatabaseUtilities.getSavedHealthcare(context);
        if (healthcareCursor != null) {
            healthcareCursor.moveToFirst();
            assertEquals(1, healthcareCursor.getInt(0));
            assertEquals(SERVICES_QUESTION, healthcareCursor.getString(1));
        } else {
            fail();
        }
    }

}