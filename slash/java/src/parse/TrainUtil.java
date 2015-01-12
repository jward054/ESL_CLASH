package parse;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

import opennlp.tools.cmdline.tokenizer.TokenizerTrainerTool;
import opennlp.tools.tokenize.TokenSample;
import opennlp.tools.tokenize.TokenSampleStream;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;
import opennlp.tools.util.eval.CrossValidationPartitioner.TrainingSampleStream;
import opennlp.uima.tokenize.TokenizerTrainer;

public class TrainUtil {

	public static void main(String[] args) throws IOException{
		Charset charset = Charset.forName("UTF-8");
		ObjectStream<String> lineStream = new PlainTextByLineStream(new FileInputStream("token-train.bin"),
		    charset);
		ObjectStream<TokenSample> sampleStream = new TokenSampleStream(lineStream);

		TokenizerModel model;

		try {
			TrainingParameters mlParams =new TrainingParameters();
		    mlParams.put(TrainingParameters.ALGORITHM_PARAM, "MAXENT");
		    mlParams.put(TrainingParameters.ITERATIONS_PARAM, Integer.toString(500));
		    mlParams.put(TrainingParameters.CUTOFF_PARAM, Integer.toString(5));
		  model = TokenizerME.train("en", sampleStream, true, mlParams);
		}
		finally {
		  sampleStream.close();
		}
		OutputStream modelOut = null;
		try {
		  modelOut = new BufferedOutputStream(new FileOutputStream("token-train.out"));
		  model.serialize(modelOut);
		} finally {
		  if (modelOut != null)
		     modelOut.close();
		}
	}

}
