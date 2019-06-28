package github.io.volong.ltr.liblinear;

import de.bwaldvogel.liblinear.*;

import java.io.File;
import java.io.IOException;

/**
 * @since 2019-06-27 15:20
 */
public class Demo {

    public static void main(String[] args) throws IOException {

        Problem problem = new Problem();
        problem.l = 1; // number of training examples
        problem.n = 1; // number of features
        // problem.x = ... // feature nodes
        // problem.y = ... // target values

        SolverType solver = SolverType.L2R_LR; // -s 0
        double C = 1.0;    // cost of constraints violation
        double eps = 0.01; // stopping criteria

        Parameter parameter = new Parameter(solver, C, eps);
        Model model = Linear.train(problem, parameter);
        File modelFile = new File("model");
        model.save(modelFile);

        // load model or use it directly
        model = Model.load(modelFile);

        Feature[] instance = { new FeatureNode(1, 4), new FeatureNode(2, 2) };
        double prediction = Linear.predict(model, instance);
    }
}
